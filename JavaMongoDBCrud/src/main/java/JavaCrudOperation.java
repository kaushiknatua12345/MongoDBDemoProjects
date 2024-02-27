import java.util.Iterator;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class JavaCrudOperation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Creating a MongoDB client
		MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
		System.out.println("Created Mongo Connection successfully");

		MongoDatabase db = mongoClient.getDatabase("HeathCareApallo");
		System.out.println("Get database is successful");

		// creating collection or get collection if exists.
		MongoCollection<Document> collection = db.getCollection("Patients");
		System.out.println("collection created ");

		// Inserting sample records by creating documents.

		Document doc = new Document("name", "Sam");
		doc.append("id", 101);
		doc.append("Billing", 10000);
		doc.append("CheckUp", "Diabetes");
		collection.insertOne(doc);
		System.out.println("Insert is completed");

		Document doc2 = new Document("name", "Joe");
		doc2.append("id", 102);
		doc2.append("Billing", 24000);
		doc2.append("CheckUp", "Liver Function Test");
		collection.insertOne(doc2);
		System.out.println("Insert is completed");

		Document doc3 = new Document("name", "Peter");
		doc3.append("id", 103);
		doc3.append("Billing", 15000);
		doc3.append("CheckUp", "Skin Problem");
		collection.insertOne(doc3);
		System.out.println("Insert is completed");

		// Listing All MongoDB Documents in Collection
		FindIterable<org.bson.Document> iterDoc = collection.find();
		int i = 1;
		// Getting the iterator
		System.out.println("Listing All Mongo Documents");
		Iterator it = iterDoc.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
			i++;
		}
		// specific document retrieving in a collection
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("name", "Joe");
		System.out.println("Retrieving specific Mongo Document");
		MongoCursor<Document> cursor = collection.find(searchQuery).iterator();
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}

		collection.updateOne(Filters.eq("name", "Joe"), Updates.set("Billing", 28000));
		System.out.println("Document updated successfully...");
		int j = 1;
		Iterator<Document> itrNew = iterDoc.iterator();
		System.out.println("Document after update...");
		while (itrNew.hasNext()) {
			System.out.println(itrNew.next());
			j++;
		}

		collection.deleteOne(Filters.eq("name", "Joe"));
		System.out.println("Document deleted successfully...");

		int k = 1;
		Iterator<Document> itrNew1 = iterDoc.iterator();
		System.out.println("Document after update...");
		while (itrNew1.hasNext()) {
			System.out.println(itrNew.next());
			k++;
		}

		// Dropping a Collection
		collection.drop();
		System.out.println("Collection dropped successfully");

	}

}
