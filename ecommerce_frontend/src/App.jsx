import { useState } from 'react'
import Header from "./components/Header.jsx";
import BreadCrumb from "./components/BreadCrumb.jsx"
import Title from "./components/Title.jsx"
import Price from "./components/Price.jsx"
import BuyButton from "./components/BuyButton.jsx"
import ShippingCalc from "./components/ShippingCalc"


function App() {

    return (
        <main className="flex flex-col gap-5">
            <header> <Header /> </header>
            <div className="ml-[9.5em] flex flex-col gap-[5em]">
                <nav>
                    <BreadCrumb path={["home", "computador", "placa de video"]} />
                </nav>
                <div className="flex gap-[4em]">
                    <figure className="p-5 border border-black">
                        <img src="https://i.imgur.com/sE5A7KP.jpeg" alt="rx6600" width="600em" />
                    </figure>
                    <div className="flex flex-col gap-4">
                        <Title>Placa de vídeo Gigabyte RX6600</Title>
                        <div className="flex flex-col gap-6">
                            <div>
                                <Price currentPrice="R$ 1.699,90" oldPrice="R$ 1.899,90" installments="em até 12x de R$142,00 sem juros" />
                            </div>
                            <div className="pb-8 border-b-2 border-black">
                                <BuyButton />
                            </div>
                            <ShippingCalc></ShippingCalc>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    )
}

export default App
