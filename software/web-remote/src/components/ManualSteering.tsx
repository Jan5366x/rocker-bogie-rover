import React from "react";
import {Stack} from "@mui/material";
import "./Remote.css";
import ManualSteeringSlider from "./ManualSteeringSlider";
import SteeringPreview from "./SteeringPreview";

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
            <SteeringPreview />
        </>
    );
}

export default ManualSteering;