import {useEffect, useState} from "react";
import {Box, Button, CircularProgress, Container, Grid, Typography} from "@mui/material";
import useAuthors from "../../hooks/useAuthors.js";
import AuthorsGrid from "../components/authors/AuthorsGrid/AuthorsGrid.jsx";
import AddAuthorDialog from "../components/authors/AddAuthorDialog/AddAuthorDialog.jsx";

const AuthorsList = () => {
    const {authors, loading, onAdd, onEdit, onDelete} = useAuthors();
    const [addAuthorDialogOpen, setAddAuthorDialogOpen] = useState(false);

    return (
        <>
            <Box className="products-box">
                {loading && (
                    <Box className="progress-box">
                        <CircularProgress/>
                    </Box>
                )}
                {!loading &&
                    <>
                        <Box sx={{display: "flex", justifyContent: "flex-end", mb: 2}}>
                            <Button variant="contained" color="primary" onClick={() => setAddAuthorDialogOpen(true)}>
                                Add Author
                            </Button>
                        </Box>
                        <AuthorsGrid authors={authors} onEdit={onEdit} onDelete={onDelete}/>
                    </>}
            </Box>
            <AddAuthorDialog
                open={addAuthorDialogOpen}
                onClose={() => setAddAuthorDialogOpen(false)}
                onAdd={onAdd}
            />

        </>
    );
};

export default AuthorsList;