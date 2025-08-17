import React, {Suspense} from "react";
import {Box} from "@mui/material";
import "./Remote.css";
import {Canvas} from "@react-three/fiber"
import {useGLTF} from "@react-three/drei"


function BodyModel() {
    const bodyModelPath = new URL("/rover-body.glb", import.meta.url).href
    const gltf = useGLTF(bodyModelPath)
    // eslint-disable-next-line react/no-unknown-property
    return <primitive object={gltf.scene}/>
}

function WheelModel() {
    const wheelModelPath = new URL("/rover-wheel.glb", import.meta.url).href
    const gltf = useGLTF(wheelModelPath)
    // eslint-disable-next-line react/no-unknown-property
    return <primitive object={gltf.scene}/>
}

function SteeringPreview() {
    return (
        <Box width={600} height={600}>
            <Canvas
                camera={{ position: [12, 11, 0], fov: 19.5, near: 1, far: 20 }}
            >
                {/* eslint-disable-next-line react/no-unknown-property */}
                <ambientLight intensity={Math.PI / 2} />
                <Suspense>
                    <BodyModel />
                    <WheelModel />
                    <WheelModel />
                    <WheelModel />
                    <WheelModel />
                    <WheelModel />
                    <WheelModel />
                </Suspense>
            </Canvas>
        </Box>
    );
}

export default SteeringPreview;