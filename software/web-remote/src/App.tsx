import React from "react";
import "./App.css";
import Remote from "./components/Remote";
import ManualSteering from "./components/ManualSteering";
import {Box} from "@mui/material";


function App() {
    return (
        <Box width={"300px"} >
            <Remote />
            <ManualSteering  />
        </Box>

    );
}

export default App;
