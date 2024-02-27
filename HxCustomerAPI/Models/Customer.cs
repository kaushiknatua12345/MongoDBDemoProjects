using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace HxCustomerAPI.Models
{
    public class Customer
    {
        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        public string? Id { get; set; }

        [BsonElement("Name")]
        public string CustomerName { get; set; } = null!;

        public decimal Billing { get; set; }

        public string Category { get; set; } = null!;

    }
}
