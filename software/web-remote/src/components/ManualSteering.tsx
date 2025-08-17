import React from "react";
import {Checkbox, FormControlLabel, FormGroup, Stack} from "@mui/material";
import "./Remote.css";
import ManualSteeringSlider from "./ManualSteeringSlider";
import SteeringPreview from "./SteeringPreview";

function ManualSteering() {
    return (
        <>
            <Stack direction={"row"} spacing={2} sx={{ margin: "20px"}}>
                <Stack direction={"column"} spacing={2} sx={{ margin: "20px"}}>
                    <ManualSteeringSlider text={"Front left"}/>
                    <ManualSteeringSlider text={"Middle left"}/>
                    <ManualSteeringSlider text={"Rear left"}/>
                </Stack>
                <Stack direction={"column"} spacing={2} sx={{ margin: "20px"}}>
                    <ManualSteeringSlider text={"Front right"}/>
                    <ManualSteeringSlider text={"Middle right"}/>
                    <ManualSteeringSlider text={"Rear right"}/>
                </Stack>
            </Stack>
            <SteeringPreview />
        </>
    );
}

export default ManualSteering;