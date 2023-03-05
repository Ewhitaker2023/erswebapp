package com.carpenoctem.controller;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.carpenoctem.model.Employees;
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
            List<Employees> employees = mapper.readValue(requestBody,
                    mapper.getTypeFactory().constructCollectionType(List.class, Employees.class));

            EmployeeService service = new EmployeeService();
            System.out.println("SELECT BY PK");
            for (Employees e : employees) {
                Employees employee = service.selectByPk(e);
                System.out.println(employee.toString());
            }

            System.out.println("SELECT ALL");
            employees = service.selectAll();
            for (Employees e : employees) {
                System.out.println(e.toString());
            }

            System.out.println("SELECT ALL BY PK");
            employees = service.selectAllByPk(employees);
            for (Employees e : employees) {
                System.out.println(e.toString());
            }

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
