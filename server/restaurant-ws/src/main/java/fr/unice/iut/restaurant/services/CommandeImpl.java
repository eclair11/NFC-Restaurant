package fr.unice.iut.restaurant.services;

import fr.unice.iut.restaurant.provider.Commande;
import fr.unice.iut.restaurant.provider.Entree;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Utilisateur on 13/04/2017.
 */

@Path("/commande")
public class CommandeImpl implements CommandeService {
// Json de la commande -> {"U_idUsers":"","T_idTables":,"Horodatage":"","details":[e1, e2, e3]}

    @Override
    public javax.ws.rs.core.Response insertCommande(String commande){
        String U_idUsers, Horodatage, T_idTables, details;
        int idUser,idTable;
        try {
			System.out.println(commande);
            JSONObject json = new JSONObject(commande);
            ArrayList<String> detailsCommande = new ArrayList<String>();
            U_idUsers = json.getString("U_idUsers");
            Horodatage = json.getString("Horodatage");
            T_idTables = json.getString("T_idTables");
            details = json.getString("details");
            System.out.println(T_idTables);
			System.out.println(U_idUsers);
			try{
            	idUser = Commande.GetIdUser(U_idUsers);
				System.out.println(idUser);
			}catch (Exception e){
            	e.printStackTrace();
				return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
			}
            try{
            	idTable = Commande.GetIdTable(T_idTables);
				System.out.println(idTable);
            }catch (Exception e){
            	 e.printStackTrace();
				return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
			}
            // Je recuperer le tableau avec les details de la commande.
            Commande.create(new Commande(idUser,Horodatage,idTable,details));
            return Response.status(201).build();
        } catch (JSONException e) {
            e.printStackTrace();
				return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (Exception e) {
            e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Override
    public javax.ws.rs.core.Response getCommande() throws JSONException{
    	try {
    		System.out.println("get Commande");
    		String json ="";
    		Commande objCommande = new Commande();
    		//  JSONObject obj = new JSONObject();
    		JSONArray arrayJson = new JSONArray();
    		for(int i = 0; i < objCommande.showCommande().size(); i++){
    			objCommande = new Commande(objCommande.showCommande().get(i).getT_idTables(),objCommande.showCommande().get(i).getHorodatage(),objCommande.showCommande().get(i).getDetails(),objCommande.showCommande().get(i).getIdCommande());
    			JSONObject obj = new JSONObject();
    			obj.put("Table",objCommande.getT_idTables());
    			obj.put("Date",objCommande.getHorodatage());
    			obj.put("Commande",objCommande.getDetails());
    			obj.put("Id",objCommande.getIdCommande());
    			arrayJson.put(obj);
    		}
    		return Response.ok(arrayJson).build();
            } catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			return Response.status(500).build();
    		}
    	}
    
    
    @Override
    public javax.ws.rs.core.Response delCommande(int idCommande) throws Exception{
	    try{
	    	System.out.println("del Commande");
	    	Commande.delete(idCommande);
	    	return Response.ok().build();
	    }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(500).build();
	    }	
    
    }
    
}
