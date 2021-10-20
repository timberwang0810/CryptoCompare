import React, {useEffect, useState} from 'react'
import CurrencyService from '../services/CurrencyService'
import classes from './component.module.css'

const CurrencyComponent = (props) => {

    const [currencies, setCurrencies] = useState([]);
    const [id, setId] = useState(props.id);
    const [name, setName] = useState("");
    const [symbol, setSymbol] = useState("");
    const [bestBuyData, setBestBuyData] = useState({
        id: "",
        name: "",
        symbol: "",
        exchange: "",
        buyPrices: {
            USDOLLAR: 0
        },
        sellPrices: {
            USDOLLAR: 0
        }
    });
    const [bestSellData, setBestSellData] = useState({
        id: "",
        name: "",
        symbol: "",
        exchange:"",
        buyPrices:{
            USDOLLAR: 0
        },
        sellPrices:{
            USDOLLAR: 0
        }
    });

    useEffect(() => {
        CurrencyService.getCurrencyById(id).then((response) => {
            console.log(response.data);
            if (response.data != null) {
                setName(response.data.name);
                setSymbol(response.data.symbol);
            }
        });
        CurrencyService.getCurrenciesById(id).then((response) => {
            console.log(response.data);
            if (response.data != null) {
                setCurrencies(response.data);
                setBestBuyData(response.data.reduce((d1, d2) => {
                    if (d1.buyPrices.USDOLLAR < d2.buyPrices.USDOLLAR){
                        return d1;
                    }
                    else{
                        return d2;
                    }
                }));
                setBestSellData(response.data.reduce((d1, d2) => {
                    if (d1.sellPrices.USDOLLAR > d2.sellPrices.USDOLLAR) {
                        return d1;
                    }
                    else {
                        return d2;
                    }
                }));
            }
        });
    });

    return (
        <div>
            <div>
                <table className={classes.PrettyTable}>
                    <thead>
                        <tr>
                            <td>Symbol</td>
                            <td>Exchange</td>
                            <td>Lowest Buy Price($)</td>
                            <td>Highest Sell Price($)</td>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            currencies.map(currency =>
                                <tr key={id + currency.exchange}>
                                    <td>{currency.id}</td>
                                    <td>{currency.exchange}</td>
                                    <td style={{ color: currency.exchange === bestBuyData.exchange ? 'green' : 'black' }}>{currency.buyPrices.USDOLLAR}</td>
                                    <td style={{ color: currency.exchange === bestSellData.exchange ? 'green' : 'black' }}>{currency.sellPrices.USDOLLAR}</td>
                                </tr>
                            )
                        }
                    </tbody>
                </table>
            </div>
            <div className={classes.Summary}>
                <h3>Buy At: {bestBuyData.exchange}</h3>
                <h3>Sell At: {bestSellData.exchange}</h3>
                <h3 style={{ color: bestSellData.sellPrices.USDOLLAR > bestBuyData.buyPrices.USDOLLAR ? 'green' : 'red'}}>
                    {bestSellData.sellPrices.USDOLLAR > bestBuyData.buyPrices.USDOLLAR ? "Profit" : "Loss"}: ${(bestSellData.sellPrices.USDOLLAR - bestBuyData.buyPrices.USDOLLAR).toFixed(2)}
                </h3>
            </div>
        </div>
    );
}

export default CurrencyComponent