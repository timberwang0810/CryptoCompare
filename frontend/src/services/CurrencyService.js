import axios from 'axios'

/**
 * NOTE: Use first link if running local build, use second link for deployment.
 */
// const CURRENCY_REST_API_URL = "http://localhost:8080/api/";
const CURRENCY_REST_API_URL = process.env.REACT_APP_API_ENDPOINT;

class CurrencyService{
    getCurrencies = () => {
        return axios.get(CURRENCY_REST_API_URL+"list");
    };
    getCurrenciesByExchange = (exchangeId) => {
        return axios.get(CURRENCY_REST_API_URL+ "exchanges/" + exchangeId);
    };
    getCurrencyById = (currencyId) => {
        return axios.get(CURRENCY_REST_API_URL + "currency/" + currencyId);
    };
    getCurrenciesById = (currencyId) => {
        return axios.get(CURRENCY_REST_API_URL + "currencies/" +currencyId);
    };
}

export default new CurrencyService()