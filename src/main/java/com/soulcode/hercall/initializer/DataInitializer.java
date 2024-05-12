package com.soulcode.hercall.initializer;

import com.soulcode.hercall.enumerator.TipoStatus;
import com.soulcode.hercall.enumerator.TipoUsuario;
import com.soulcode.hercall.models.Chamado;
import com.soulcode.hercall.models.Prioridade;
import com.soulcode.hercall.models.Setor;
import com.soulcode.hercall.models.Usuario;
import com.soulcode.hercall.repositories.ChamadoRepository;
import com.soulcode.hercall.repositories.PrioridadeRepository;
import com.soulcode.hercall.repositories.SetorRepository;
import com.soulcode.hercall.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private SetorRepository setorRepository;

    @Autowired
    private PrioridadeRepository prioridadeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ChamadoRepository chamadoRepository;

    public void run(String... args) throws Exception {
        if (setorRepository.count() == 0 && prioridadeRepository.count() == 0 &&
                usuarioRepository.count() == 0 && chamadoRepository.count() == 0) {
            initSetores();
            initPrioridades();
            initUsuarios();
            initChamados();
        }
    }

    private void initSetores() {
        String[] tipoSetor = {"Suporte", "Financeiro", "Markenting", "RH", "Departamento pessoal", "Desenvolvimento web"};

        for (String tipo : tipoSetor) {
            Setor setor = new Setor();
            setor.setTipoSetor(tipo);
            setorRepository.save(setor);
        }
    }

    private void initPrioridades() {
        String[] tipoPrioridade = {"Baixa", "Média", "Alta", "Urgente"};

        for (String tipo : tipoPrioridade) {
            Prioridade prioridade = new Prioridade();
            prioridade.setTipoPrioridade(tipo);
            prioridadeRepository.save(prioridade);
        }
    }

    private void initUsuarios() {
        String[] matriculas = {"123", "456", "789"};
        String[] nomes = {"Funcionario1", "Admin1", "Tecnico1"};
        String[] emails = {"funcionario1@example.com", "admin1@example.com", "tecnico1@example.com"};
        String[] senhas = {"senha123", "senha456", "senha789"};

        TipoUsuario[] tiposUsuario = {TipoUsuario.FUNCIONARIO, TipoUsuario.ADMIN, TipoUsuario.TECNICO};

        for (int i = 0; i < matriculas.length; i++) {
            Usuario usuario = new Usuario();
            usuario.setMatricula(matriculas[i]);
            usuario.setNome(nomes[i]);
            usuario.setEmail(emails[i]);
            usuario.setSenha(senhas[i]);
            usuario.setTipo_usuario(tiposUsuario[i]);

            usuarioRepository.save(usuario);
        }
    }

    private void initChamados() {
        Random random = new Random();

        List<Prioridade> prioridades = prioridadeRepository.findAll();
        List<Setor> setores = setorRepository.findAll();
        List<Usuario> usuarios = usuarioRepository.findAll();
        TipoStatus[] tiposStatus = {TipoStatus.AGUARDANDO_TECNICO, TipoStatus.EM_ATENDIMENTO,
                TipoStatus.FINALIZADO, TipoStatus.ESCALADO_PARA_OUTRO_SETOR, TipoStatus.CANCELADO, TipoStatus.AGUARDANDO_TECNICO, TipoStatus.EM_ATENDIMENTO,
                TipoStatus.FINALIZADO, TipoStatus.ESCALADO_PARA_OUTRO_SETOR, TipoStatus.FINALIZADO, TipoStatus.FINALIZADO, TipoStatus.CANCELADO
        };

        int YEAR = LocalDate.now().getYear();

        for (int i = 0; i < tiposStatus.length; i++) {
            int month = i + 1;
            Chamado chamado = new Chamado();
            chamado.setDescricao("Chamado " + month + " - Descrição");
            chamado.setData_inicio(LocalDate.of(YEAR, month, 1));
            chamado.setStatus(tiposStatus[i]);
            chamado.setSetor(setores.get(random.nextInt(setores.size())));
            chamado.setSolicitante(usuarios.get(0));
            chamado.setResponsavel(usuarios.get(2));
            chamado.setPrioridade(prioridades.get(random.nextInt(prioridades.size())));

            chamadoRepository.save(chamado);
        }
    }

}
