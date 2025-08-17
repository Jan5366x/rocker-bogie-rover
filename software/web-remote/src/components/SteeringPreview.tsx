import React, {Suspense} from "react";
import {Box} from "@mui/material";
import "./Remote.css";
import {Canvas} from "@react-three/fiber"
import {useGLTF} from "@react-three/drei"


function Model() {
    const modelPath = new URL("/rover-body.glb", import.meta.url).href
    const gltf = useGLTF(modelPath)
    // eslint-disable-next-line react/no-unknown-property
    return <primitive object={gltf.scene} />
}


function SteeringPreview() {

    return (
        <Box width={600} height={600}>
            <Canvas
                camera={{ position: [12, 11, 0], fov: 19.5, near: 1, far: 20 }}
            >
                <ambientLight />
                <Suspense>
                    <Model />
                </Suspense>
            </Canvas>
        </Box>
    );
}

export default SteeringPreview;