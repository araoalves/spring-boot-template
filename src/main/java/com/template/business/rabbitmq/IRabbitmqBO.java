package com.template.business.rabbitmq;

import com.template.model.Cliente;

public interface IRabbitmqBO {
    Cliente enviarClienteRebbitMq(Cliente cliente);
}
