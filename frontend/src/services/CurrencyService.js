import axios from 'axios'

const CURRENCY_REST_API_URL = "http://localhost:8080/api/";

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