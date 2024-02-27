using HxCustomerAPI.Models;
using HxCustomerAPI.Services;

var builder = WebApplication.CreateBuilder(args);

//add Entity Model Services to the container
builder.Services.Configure<HxCustomersDatabaseSettings>(
    builder.Configuration.GetSection("CustomerDatabase"));

builder.Services.AddSingleton<CustomerService>();

// Add services to the container.

builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseAuthorization();

app.MapControllers();

app.Run();
