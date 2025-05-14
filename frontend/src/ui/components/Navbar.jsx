import * as React from 'react';
import {Link} from 'react-router-dom';
import {AppBar, Box, Button, Container, IconButton, Toolbar, Tooltip, Typography} from '@mui/material';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';

const pages = [
    {name: 'Home', path: '/'},
    {name: 'Books', path: '/books'},
    {name: 'Authors', path: '/authors'},
    {name: 'Countries', path: '/countries'},
];


function Navbar() {

    return (
        <AppBar position="fixed">
            <Container maxWidth="xl">
                <Toolbar disableGutters>
                    <Typography
                        variant="h6"
                        noWrap
                        component={Link}
                        to="/"
                        sx={{
                            mr: 2,
                            fontWeight: 700,
                            color: 'inherit',
                            textDecoration: 'none',
                        }}
                    >
                        eShop
                    </Typography>

                    <Box sx={{flexGrow: 1, display: {xs: 'none', md: 'flex'}}}>
                        {pages.map((page) => (
                            <Button
                                key={page.name}
                                component={Link}
                                to={page.path}
                                sx={{my: 2, color: 'white', display: 'block'}}
                            >
                                {page.name}
                            </Button>
                        ))}
                    </Box>

                    <Box sx={{flexGrow: 0}}>
                        <Tooltip title="Cart">
                            <IconButton component={Link} to="/cart/1" color="inherit">
                                <ShoppingCartIcon/>
                            </IconButton>
                        </Tooltip>
                    </Box>
                </Toolbar>
            </Container>
        </AppBar>
    );
}

export default Navbar;
