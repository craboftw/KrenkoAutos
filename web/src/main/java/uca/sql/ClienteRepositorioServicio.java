package uca.sql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uca.core.dominio.Cliente;


@Service
public class ClienteRepositorioServicio {

    private ClienteDao clienteDao;

    @Autowired
    public ClienteRepositorioServicio(ClienteDao clienteDao)
    {
        this.clienteDao = clienteDao;
    }

   // @Override
    @Transactional(readOnly = true)
    public List<Cliente> listarClientes() {
        return (List<Cliente>) clienteDao.findAll();
    }

   // @Override
    @Transactional
    public void guardar(Cliente Cliente) {
        clienteDao.save(Cliente);
    }

   // @Override
    @Transactional
    public void eliminar(Cliente Cliente) {
        clienteDao.delete(Cliente);
    }

   // @Override
    @Transactional(readOnly = true)
    public Cliente encontrarCliente(Cliente Cliente) {
        return clienteDao.findById(Cliente.getIdC()).orElse(null);
    }
}
