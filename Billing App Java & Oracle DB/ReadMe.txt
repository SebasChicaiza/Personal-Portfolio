This lightweight billing system, developed in Java, is designed to simulate the operations of a real supermarket. The application connects to a local database to manage product availability and track cashier performance, with commissions calculated for each sale.

### Key Features

- **Database Integration**: The system requires a local database connection to handle product inventory and cashier management. The database enforces restrictions such as preventing the sale of nonexistent products or quantities that exceed available stock. 
- **IDE Compatibility**: To explore and navigate the application's features, open the project using Apache NetBeans IDE 19 or later.
  
### Database Considerations

Since the logic for enforcing restrictions resides in the database, careful attention should be given when setting it up. A relational database, such as Oracle, is recommended for optimal performance and management of business rules.

### Project Information

This project was developed by David Erazo and Sebastian Chicaiza for the Database I course at PUCE.