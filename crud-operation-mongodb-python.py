from pymongo import MongoClient

client = MongoClient('localhost', 27017)

# Choose a database (it will be created if it doesn’t exist)
db = client.pythonMongoDatabase

# Choose a collection (similar to a table in relational databases)
collection = db.pythonMongoCustomerDatabase

#Insert data
docs = [
    {"name": "Kaushik", "age": 38, "country": "India"},
    {"name": "Rohini", "age": 28, "country": "India"},
    {"name": "Michael", "age": 32, "country": "Poland"}
]
result = collection.insert_many(docs)
print(result.inserted_ids)

#Retrieve Data
# Find all documents
result = collection.find()
print(result)

# Find one document with a criteria
result = collection.find({"name": "Kaushik"})
print(result)

#Update document
# Update the age of Michael
criteria = {"name": "Michael"}
new_values = {"$set": {"age":35}}
collection.update_one(criteria, new_values)

# Delete Document
collection.delete_one({"name": "Rohini"})
print('Data deletion successful')

client.close()

