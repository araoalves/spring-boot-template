package com.template.controller.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.template.business.rabbitmq.IRabbitmqBO;
import com.template.model.Cliente;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@CrossOrigin()
@Profile("rabbitmq")
@RequestMapping(value = "/rabbitmq")
@Api(value="rabbitmq")
public class RabbitmqController {

    @Autowired
    private IRabbitmqBO rabbitmqBO;

    @ApiOperation(value = "Enviar Cliente RebbitMq", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/enviarClienteRebbitMq", method = RequestMethod.POST)
    public ResponseEntity<?> enviarClienteRebbitMq(@RequestBody Cliente cliente) throws Exception {
        try {
            return new ResponseEntity<>(rabbitmqBO.enviarClienteRebbitMq(cliente), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

}
