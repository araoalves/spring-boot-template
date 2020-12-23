package com.template.controller.kafka;

import com.template.business.IClienteBO;
import com.template.business.kafka.ISendClientKafka;
import com.template.model.Cliente;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping(value = "/kafka")
@Api(value="kafka")
@Profile("kafka")
public class KafkaController {
    @Autowired
    private ISendClientKafka clientKafka;

    @ApiOperation(value = "Enviar Cliente Kafka", authorizations = { @Authorization(value="apiKey") })
    @RequestMapping(value = "/enviarClienteKafka", method = RequestMethod.POST)
    public ResponseEntity<?> enviarClienteKafka(@RequestBody Cliente cliente) throws Exception {
        try {
            return new ResponseEntity<>(clientKafka.sendClientKafka(cliente), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }
}
