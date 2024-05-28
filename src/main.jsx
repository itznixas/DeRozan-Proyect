import React from 'react'
import ReactDOM from 'react-dom/client'
import {NextUIProvider} from "@nextui-org/react";

import './index.css'
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import HomeRoute  from './routes/HomeRoute'
import LoginRoute from './routes/LoginRoute';
import DashboardRoutes from './routes/DashboardRoute';
import ProtecteRoute from './routes/ProtecteRoute';
import Error from './routes/Error';

const router = createBrowserRouter([  
  {
    path: "/",
    element: <HomeRoute/>,
    errorElement: <Error/>
  },
  
  {
    path: "/",
    element: <ProtecteRoute/>,
    children: [
    {
      path: "/Dashboard",
      element: <DashboardRoutes/>,
      },
      {
        path: "/login",
        element: <LoginRoute/>,
      },
    ],
    
  },
]);






ReactDOM.createRoot(document.getElementById('root')).render(

<NextUIProvider>
<RouterProvider router={router} /> 
</NextUIProvider>
)
