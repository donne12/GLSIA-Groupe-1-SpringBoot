import React from 'react';
import {BrowserRouter,Routes, Route} from 'react-router-dom';
import Layout from './components/layout';
import User from './components/User/User';
import Approv from './components/Approv/Approv';
import Article from './components/Article/Article';
import Vente from './components/Vente/Vente';
import LigneVente from './components/LigneVente/LigneVente';
import Categorie from './components/Categorie/Categorie';
import ErrorPage from './components/ErrorPage';
import Footer from './components/Footer';
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min.js";

//import './App.css';

class App extends React.Component {

  render () {
    return (
      
  <BrowserRouter>
    <Layout/>
    <Routes>
      <Route path="/" element={<User/>}/>   
      <Route path="/categorie" element={<Categorie/>}/>
      <Route path="/article" element={<Article/>}/>
      <Route path="/approv" element={<Approv/>}/>
      <Route path="/vente" element={<Vente/>}/>
      <Route path="/ligneVente" element={<LigneVente/>}/>
      <Route path="*" element={<ErrorPage/>}/>
    </Routes> 
    <Footer/>
  </BrowserRouter>  
          );
  }
}


export default App;
