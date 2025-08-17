import React from "react";
import {Box, Grid, Input, Slider, Typography} from "@mui/material";
import "./Remote.css";
import RotateLeftIcon from "@mui/icons-material/RotateLeft";

function ManualSteering() {
    const valueMin = -90;
    const valueMax = 90;
    const valueDefault= 0;
    const valueFallback = 0;
    const [value, setValue] = React.useState(valueDefault);

    const handleSliderChange = (event: Event, newValue: number) => {
        setValue(newValue);
    };

    const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setValue(event.target.value === "" ? valueFallback : Number(event.target.value));
    };

    const handleBlur = () => {
        if (value < valueMin) {
            setValue(valueMin);
        } else if (value > valueMax) {
            setValue(valueMax);
        }
    };

    return (
        <Box sx={{ width: 250 }}>
            <Typography id="input-slider" gutterBottom>
                Text
            </Typography>
            <Grid container spacing={2} sx={{ alignItems: "center" }}>
                <Grid>
                    <RotateLeftIcon />
                </Grid>
                <Grid size="grow">
                    <Slider
                        value={typeof value === "number" ? value : valueFallback}
                        onChange={handleSliderChange}
                        aria-labelledby="input-slider"
                        min={valueMin}
                        max={valueMax}
                        defaultValue={valueDefault}
                        track={false}
                    />
                </Grid>
                <Grid>
                    <Input
                        value={value}
                        size="small"
                        onChange={handleInputChange}
                        onBlur={handleBlur}
                        inputProps={{
                            step: 1,
                            min: valueMin,
                            max: valueMax,
                            type: "number",
                            "aria-labelledby": "input-slider",
                        }}
                    />
                </Grid>
            </Grid>
        </Box>
    );
}

export default ManualSteering;