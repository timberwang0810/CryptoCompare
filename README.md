# CryptoCompare
A simple React app to compare prices of Bitcoin and Ethereum from Blockchain.com and Gemini.

## Live Link

This app is deployed via Heroku:



## Running the Build

1. Clone this repository, open two terminal windows. One navigate into CryptoCompare (backend), one navigate into CryptoCompare/frontend

2. On the backend terminal, run the backend application by:
    ```
    mvn spring-boot:run 
    ```

Make sure you have java and maven installed: 
    Windows: https://mkyong.com/maven/how-to-install-maven-in-windows/ 
    MacOS: https://mkyong.com/maven/install-maven-on-mac-osx/

The backend will be running on port 8080. Go to http://localhost:8080/api/list to see a JSON list of currencies

3. On the frontend terminal, run the frontend React application by:
    ```
    npm start
    ```
Make sure you have Node.js installed: https://nodejs.org/en/

The frontend will be running on port 3000. The above command should launch and open the app automatically in your default browser!
