package com.example.demosoap.endpoint;

import com.example.demosoap.converter.EmpleadoConverter;
import com.example.demosoap.gen.Empleado;
import com.example.demosoap.gen.PostEmpleadoRequest;
import com.example.demosoap.gen.PostEmpleadoResponse;
import com.example.demosoap.model.EmpleadoModel;
import com.example.demosoap.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.datatype.DatatypeConfigurationException;

@Endpoint
public class ProductEndpoint {

    private static final String NAMESPACE_URI = "http://www.example.com/demosoap/gen";

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private EmpleadoConverter empleadoConverter;
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "postEmpleadoRequest")
    @ResponsePayload
    public PostEmpleadoResponse postEmpleados(@RequestPayload PostEmpleadoRequest request) throws DatatypeConfigurationException {
        PostEmpleadoResponse response = new PostEmpleadoResponse();
        EmpleadoModel empleadoModel = empleadoConverter.convertEmpleadoToEmpleadoModel(request.getEmpleado());
        Empleado empleado = empleadoConverter.convertEmpleadoModelToProduct(empleadoRepository.save(empleadoModel));
        response.setEmpleado(empleado);
        return response;
    }
}
