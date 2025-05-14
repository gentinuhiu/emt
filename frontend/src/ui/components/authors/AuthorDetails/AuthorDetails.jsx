import React from 'react';
import {useNavigate, useParams} from "react-router";

import {
    Box,
    Button,
    Chip,
    CircularProgress,
    Grid,
    Typography,
    Paper,
    Avatar,
    Stack,
    Rating,
    Breadcrumbs,
    Link
} from "@mui/material";
import {
    ArrowBack,
    Category,
    Factory,
    Star,
    ShoppingCart,
    FavoriteBorder,
    Share
} from "@mui/icons-material";
import useAuthorDetails from "../../../../hooks/useAuthorDetails.js";

const AuthorDetails = () => {
    const navigate = useNavigate();
    const {id} = useParams();
    const {author, country} = useAuthorDetails(id);

    if (!author || !country) {
        return (
            <Box sx={{display: 'flex', justifyContent: 'center', alignItems: 'center', minHeight: '60vh'}}>
                <CircularProgress/>
            </Box>
        );
    }

    return (
        <Box>
            <Breadcrumbs aria-label="breadcrumb" sx={{mb: 3}}>
                <Link
                    underline="hover"
                    color="inherit"
                    href="#"
                    onClick={(e) => {
                        e.preventDefault();
                        navigate("/authors");
                    }}
                >
                    Books
                </Link>
                <Typography color="text.primary">{author.name} {author.surname}</Typography>
            </Breadcrumbs>

            <Paper elevation={2} sx={{p: 4, borderRadius: 4}}>
                <Grid container spacing={4}>
                    <Grid size={{xs: 12, md: 9}}>
                        <Box sx={{mb: 3}}>
                            <Typography variant="h3" gutterBottom sx={{fontWeight: 600}}>
                                {author.name} {author.surname}
                            </Typography>
                            <Typography variant="body1" sx={{mb: 3}}>
                                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Animi consequatur culpa
                                doloribus, enim maiores possimus similique totam ut veniam voluptatibus. Adipisci
                                consequatur, cum dolorem eaque enim explicabo fugit harum, id laborum non totam ut!
                                Architecto assumenda deserunt doloribus ducimus labore magnam officiis sunt. Autem culpa
                                eaque obcaecati quasi, quo vitae.
                            </Typography>

                            <Stack direction="row" spacing={1} sx={{mb: 3}}>
                                <Typography>
                                    Born in: {author.country.name}, {author.country.continent}
                                </Typography>
                            </Stack>
                        </Box>
                    </Grid>
                </Grid>
            </Paper>
        </Box>
    );
};

export default AuthorDetails;
