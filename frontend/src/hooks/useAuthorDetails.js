import {useEffect, useState} from "react";
import authorRepository from "../repository/authorRepository.js";
import countryRepository from "../repository/countryRepository.js";


const useAuthorDetails = (id) => {
    const [state, setState] = useState({
        "author": null,
        "country": null,
    });

    useEffect(() => {
        authorRepository
            .findById(id)
            .then((response) => {
                setState(prevState => ({...prevState, "author": response.data}));
                // categoryRepository
                //     .findById(response.data.categoryId)
                //     .then((response) => {
                //         setState(prevState => ({...prevState, "category": response.data}));
                //     })
                //     .catch((error) => console.log(error));
                countryRepository
                    .findById(response.data.country.id)
                    .then((response) => {
                        setState(prevState => ({...prevState, "country": response.data}));
                    })
                    .catch((error) => console.log(error));
            })
            .catch((error) => console.log(error));
    }, [id]);

    return state;
};

export default useAuthorDetails;