package com.carpenoctem.controller;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.carpenoctem.model.Employee;
import com.carpenoctem.service.EmployeeService;
import com.carpenoctem.util.StringBuilderUtil;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class FrontController implements HttpHandler {

    private void postRequest(HttpExchange exchange) {
        try {
            String requestBody = StringBuilderUtil.build(exchange.getRequestBody()).toString();

            System.out.println(requestBody);

            ObjectMapper mapper = new ObjectMapper();
            Employee employee = mapper.readValue(requestBody, Employee.class);

            System.out.println(employee.toString());

            EmployeeService service = new EmployeeService();
            employee = service.save(employee);

            System.out.println(employee.toString());

            exchange.sendResponseHeaders(200, -1);
            exchange.close();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(HttpExchange exchange) {
        String verb = exchange.getRequestMethod();
        switch (verb) {
            case "POST":
                postRequest(exchange);
                break;
            default:
                break;
        }
    }
}
