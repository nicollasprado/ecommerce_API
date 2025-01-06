import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import { createBrowserRouter, RouterProvider } from 'react-router'
import { QueryClient, QueryClientProvider } from '@tanstack/react-query'

import Home from "./routes/Home.jsx"
import ProductPage from "./routes/ProductPage.jsx"


const client = new QueryClient();

const router = createBrowserRouter([
    {
        element: <App />,
        children: [
            {
                path: "/",
                element: <Home />,
            },
            {
                path: "/product/",
                element: <ProductPage />,
            },
        ]
    },
])


createRoot(document.getElementById('root')).render(
  <StrictMode>
    <QueryClientProvider client = {client}>
        <RouterProvider router={router} />
    </QueryClientProvider>
  </StrictMode>,
)
