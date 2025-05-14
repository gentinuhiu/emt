import bookRepository from "../repository/bookRepository.js";
import authorRepository from "../repository/authorRepository.js";
import {useEffect, useState} from "react";

const useBookDetails = (id) => {
    const [state, setState] = useState({
        "book": null,
        "author": null,
        // "category": null,
    });

    useEffect(() => {
        bookRepository
            .findById(id)
            .then((response) => {
                setState(prevState => ({...prevState, "book": response.data}));
                // categoryRepository
                //     .findById(response.data.categoryId)
                //     .then((response) => {
                //         setState(prevState => ({...prevState, "category": response.data}));
                //     })
                //     .catch((error) => console.log(error));
                authorRepository
                    .findById(response.data.author.id)
                    .then((response) => {
                        setState(prevState => ({...prevState, "author": response.data}));
                    })
                    .catch((error) => console.log(error));
            })
            .catch((error) => console.log(error));
    }, [id]);

    return state;
};

export default useBookDetails;
