import React from "react";
import Joystick from "rc-joystick";
import {Stack, Typography} from "@mui/material";
import "./Remote.css";

function Remote() {
    return (
        <>
            <Stack direction={"row"} spacing={2}>
                <Joystick />
                <Typography>Status: Offline</Typography>
            </Stack>
        </>
    );
}

export default Remote;