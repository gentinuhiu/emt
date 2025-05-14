import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import {Link} from 'react-router-dom';
import {Route, Routes} from "react-router-dom";
import Navbar from "./ui/components/Navbar.jsx";
import {Container} from "@mui/material";
import './App.css'
import HomePage from "./ui/pages/HomePage.jsx";
import AuthorsList from "./ui/pages/AuthorsList.jsx";
import BooksList from './ui/pages/BooksList.jsx';
import BookDetails from "./ui/components/books/BookDetails/BookDetails.jsx";
import AuthorDetails from "./ui/components/authors/AuthorDetails/AuthorDetails.jsx";


function CountriesList() {
    return null;
}

function App() {
  return (
      <>
        <Navbar/>
          <Container sx={{pt: 10}}>
              <Routes>
                  <Route path="/" element={<HomePage/>}/>
                  <Route path="/books" element={<BooksList/>}/>
                  <Route path="/books/:id" element={<BookDetails/>}/>
                  <Route path="/authors" element={<AuthorsList/>}/>
                  <Route path="/authors/:id" element={<AuthorDetails/>}/>
                  <Route path="/countries" element={<CountriesList/>}/>
              </Routes>
          </Container>
      </>
  );
}

export default App
