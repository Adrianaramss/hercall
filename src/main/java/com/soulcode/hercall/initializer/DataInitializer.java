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
        }
    }

    private void initSetores() {
        String[] tipoSetor = {"Suporte", "Financeiro", "Marketing", "RH", "Departamento pessoal", "Desenvolvimento web"};

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
        String[] matriculas = {"123"};
        String[] nomes = {"Maria"};
        String[] emails = {"maria@admin.com"};
        String[] senhas = {"senha123"};

        TipoUsuario[] tiposUsuario = {TipoUsuario.ADMIN};

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

}
