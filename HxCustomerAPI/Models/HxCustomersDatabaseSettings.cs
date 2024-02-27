namespace HxCustomerAPI.Models
{
    public class HxCustomersDatabaseSettings
    {
        public string ConnectionString { get; set; } = null!;

        public string DatabaseName { get; set; } = null!;

        public string CustomersCollectionName { get; set; } = null!;
    }
}
