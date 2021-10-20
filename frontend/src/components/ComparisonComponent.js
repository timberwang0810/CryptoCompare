import React from 'react'
import CurrencyComponent from './CurrencyComponent'
import bitcoin from '../img/bitcoin.png'
import crypto from '../img/crypto.png'
import ethereum from '../img/ethereum.png'
import classes from './component.module.css'

const ComparisonComponent = (props) => {

    // refreshData(){
    //     CurrencyService.getCurrencies().then((response) => {
    //         console.log(response.data);
    //         this.setState({
    //             currencies: response.data
    //         })
    //     });
    // }

    return (
        <div style={{
            position: 'absolute', left: '50%', top: '50%',
            transform: 'translate(-50%, -50%)'
        }}>
            <div>
                <h1 className="text-center">
                    <img src={crypto} style={{ width: 80, height: 80, marginRight: 10 }}/>
                    Crypto Compare
                </h1>
            </div>
            <table align="center" >
                <thead className={classes.NormalTable}>
                    <tr>
                        <td><img src={bitcoin} style={{ width: 32, height: 32, marginRight: 10 }}/><b>Bitcoin</b></td>
                        <td><img src={ethereum} style={{ width: 32, height: 32, marginRight: 10 }} /><b>Ethereum</b></td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td key="btc"><CurrencyComponent id="btc" /></td>
                        <td key="eth"><CurrencyComponent id="eth" /></td>
                    </tr>
                </tbody>
            </table>
            {/* <button onClick={this.refreshData}></button> */}
        </div>
    )
}

export default ComparisonComponent