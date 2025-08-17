import React from "react";
import {Stack} from "@mui/material";
import "./Remote.css";
import ManualSteeringSlider from "./ManualSteeringSlider";

function ManualSteering() {
    return (
        <>
            <Stack direction={"row"} spacing={2} sx={{ margin: "20px"}}>
                <Stack direction={"column"} spacing={2} sx={{ margin: "20px"}}>
                    <ManualSteeringSlider />
                    <ManualSteeringSlider />
                    <ManualSteeringSlider />

                </Stack>
                <Stack direction={"column"} spacing={2} sx={{ margin: "20px"}}>
                    <ManualSteeringSlider />
                    <ManualSteeringSlider />
                    <ManualSteeringSlider />
                </Stack>
            </Stack>
        </>
    );
}

export default ManualSteering;