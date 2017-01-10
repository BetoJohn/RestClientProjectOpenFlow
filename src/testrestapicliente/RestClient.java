/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testrestapicliente;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Scanner;

/**
 *
 * @author Beto
 */
public class RestClient {

    private static String ipController = "http://192.168.25.8:8080";

    public static void main(String[] args) throws MalformedURLException, IOException {

        /**
         * *
         * /wm/core/memory/json /wm/statistics/config/enable/json
         * /wm/core/version/json
         *
         * /wm/topology/route/<src-dpid>/<src-port>/<dst-dpid>/<dst-port>/json
         */
        Scanner sc = new Scanner(System.in);
        int escolha = -1;

        while (escolha != 0) {
            menu();
            escolha = sc.nextInt();

            switch (escolha) {
                case 0:
                    postSwitch(ipController + "/wm/statistics/config/disable/json");
                    break;
                case 1:
                    postSwitch(ipController + "/wm/statistics/config/enable/json");
                    break;
                case 2:
                    postSwitch(ipController + "/wm/statistics/config/disable/json");
                    break;
                case 3:
                    getSwitch(ipController + "/wm/statistics/bandwidth/all/all/json");
                    break;
                case 4:
                    getSwitch(ipController + "/wm/core/controller/switches/json");
                    break;

                case 5:
                    System.out.println("Informe o Id do Switch:");
                    System.out.println("Exemplo: 00:00:00:00:00:00:00:01");
                    System.out.println(" ");
                    System.out.print("-> ");
                    String idSwitch = sc.next();
                    getSwitch(ipController + "/wm/statistics/bandwidth/" + idSwitch + "/all/json");
                    break;

                case 6:
                    getSwitch(ipController + "/wm/topology/switchclusters/json");
                    break;

                case 7:
                    System.out.println("statType: aggregate, desc, flow, group, "
                            + "group-desc, group-features, meter, meter-config, "
                            + "meter-features, port, port-desc, queue, table, features  ");

                    System.out.println(" ");
                    System.out.print("-> ");
                    String statType = sc.next();
                    getSwitch(ipController + "/wm/core/switch/all/" + statType + "/json");
                    break;

                case 8:
                    System.out.println("Informe o Id do Switch:");
                    System.out.println("Exemplo: 00:00:00:00:00:00:00:01");
                    System.out.println(" ");
                    System.out.print("-> ");
                    String dpIdSwitch = sc.next();
                    System.out.println(" ");
                    System.out.println("Informe uma porta válida:");
                    System.out.println(" ");
                    System.out.print("-> ");
                    String port = sc.next();

                    getSwitch(ipController + "/wm/statistics/bandwidth/" + dpIdSwitch + "/" + port + "/json");
                    break;

                case 9:
                    System.out.println("Informe o Id do Switch de Origem:");
                    System.out.println("Exemplo: 00:00:00:00:00:00:00:01");
                    System.out.println(" ");
                    System.out.print("-> ");
                    String dpIdSwitchOrigem = sc.next();
                    System.out.println(" ");
                    System.out.println("Informe uma porta válida de origem:");
                    System.out.println(" ");
                    System.out.print("-> ");
                    String portOrigem = sc.next();
                    System.out.println(" ");
                    System.out.println("Informe o Id do Switch de Destino:");
                    System.out.println("Exemplo: 00:00:00:00:00:00:00:01");
                    System.out.println(" ");
                    System.out.print("-> ");
                    String dpIdSwitchDestino = sc.next();
                    System.out.println(" ");
                    System.out.println("Informe uma porta válida de destino:");
                    System.out.println(" ");
                    System.out.print("-> ");
                    String portDestino = sc.next();

                    getSwitch(ipController + "/wm/topology/route/"+dpIdSwitchOrigem+"/"+portOrigem+"/"+dpIdSwitchDestino+"/"+portDestino+"/json");
                    break;

            }

        }

    }

    private static void getSwitch(String url) {
        try {
            Client client = Client.create();
            WebResource webResource = client.resource(url);
            ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
            }
            String output = response.getEntity(String.class);
            System.out.println(output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void postSwitch(String url) {
        try {

            Client client = Client.create();

            WebResource webResource = client
                    .resource(url);

            //tive que acrescentar o accept para poderativar a captura de statistics
            ClientResponse response = webResource.type("application/json").accept("application/json")
                    .post(ClientResponse.class);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            String output = response.getEntity(String.class);
            System.out.println(output);

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    private static void menu() {
        System.out.println(" ");
        System.out.println("-----------<MENU>-----------");
        System.out.println("(1) - Ativar");
        System.out.println("(2) - Desativar");
        System.out.println("(3) - Estatisticas todos Switchs");
        System.out.println("(4) - Switchs");
        System.out.println("(5) - Estatisticas por Id Switch");
        System.out.println("(6) - Topologia");
        System.out.println("(7) - Tipos de  Estatísticas");
        System.out.println("(8) - Largura de Banda Consumida");
        System.out.println("(9) - Rota definida pelo switch de origem para o de destino");
        System.out.println("(0) - Sair");
        System.out.println(" ");
        System.out.print("-> ");
    }
}
