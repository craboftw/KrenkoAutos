package Uca.SpringCli.Implementaciones;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonProperty;

import Uca.Core.Cliente;
import Uca.Core.ClienteRepositorio;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;



public class ClienteRepositorioJackson implements ClienteRepositorio {

String CLIENTES_FILE_PATH = "clientes.json";


    @Override
    public void guardarCliente(Collection<Cliente> clientes) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
            objectMapper.addMixIn(Cliente.class, ClienteMixIn.class);
            objectMapper.writeValue(new File(CLIENTES_FILE_PATH), clientes);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Collection<Cliente> cargarCliente() {
        try {
            File file = new File(CLIENTES_FILE_PATH);
            if (file.exists()) {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.addMixIn(Cliente.class, ClienteMixIn.class); // register the ClienteMixIn class
                return objectMapper.readValue(file, new TypeReference<Collection<Cliente>>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

        abstract class ClienteMixIn {
            @JsonProperty("dni")
            abstract String getDni();

            @JsonUnwrapped
            abstract Cliente getPersona();
        }

        @Override
        public void guardarCliente(Cliente cliente) {
            // TODO Auto-generated method stub
            Collection<Cliente> clientes = cargarCliente();
            clientes.add(cliente);
            guardarCliente(clientes);
        }

    }

    //crear-cliente Violeta ai 666555669 2000-07-16 6969696969p violeton@chocheteregordete.pussy




