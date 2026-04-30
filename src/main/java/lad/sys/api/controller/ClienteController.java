package lad.sys.api.controller;

import lad.sys.api.cliente.Cliente;
import lad.sys.api.cliente.ClienteRepository;
import lad.sys.api.cliente.DadosCadastroCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public String get() {
        return "GET Cliente";
    }

    @PostMapping
    public String post(@RequestBody DadosCadastroCliente dados) {
        clienteRepository.save(new Cliente(dados));
        return "POST Cliente";
    }

    @PutMapping
    public String put() {
        return "PUT Cliente";
    }
}
