using System;
using UnityEngine;

[ExecuteInEditMode]
public class RoverMovement : MonoBehaviour
{
    public Vector3 centerOfTurningCircle;
    public Vector3 targetPosition;
    
    private Transform steeringAxleFrontRight;
    private Transform steeringAxleMiddleRight;
    private Transform steeringAxleBackRight;

    private Transform steeringAxleFrontLeft;
    private Transform steeringAxleMiddleLeft;
    private Transform steeringAxleBackLeft;
    

    void Start()
    {
        FindSteeringAxles();
    }

    void Awake()
    {
        FindSteeringAxles();
    }

    private void FindSteeringAxles()
    {
        steeringAxleFrontRight = transform.Find("RightRocker/Bogie/steering axle front");
        steeringAxleFrontLeft = transform.Find("LeftRocker/Bogie/steering axle front");
        
        steeringAxleMiddleRight = transform.Find("RightRocker/Bogie/steering axle middle");
        steeringAxleMiddleLeft = transform.Find("LeftRocker/Bogie/steering axle middle");

        steeringAxleBackRight = transform.Find("RightRocker/steering axle back");
        steeringAxleBackLeft = transform.Find("LeftRocker/steering axle back");
    }


    void Update()
    {
        // TODO Ackermann steering geometry
        //        https://en.wikipedia.org/wiki/Ackermann_steering_geometry

        FindSteeringAxles();
        
        Debug.DrawLine(new Vector3(steeringAxleFrontRight.position.x, 0f, steeringAxleFrontRight.position.z) , centerOfTurningCircle, Color.darkOrange);
        Debug.DrawLine(new Vector3(steeringAxleFrontLeft.position.x, 0f, steeringAxleFrontLeft.position.z) , centerOfTurningCircle, Color.yellow);
        Debug.DrawLine(new Vector3(steeringAxleMiddleRight.position.x, 0f, steeringAxleMiddleRight.position.z) , centerOfTurningCircle, Color.darkOrange);
        Debug.DrawLine(new Vector3(steeringAxleMiddleLeft.position.x, 0f, steeringAxleMiddleLeft.position.z) , centerOfTurningCircle, Color.yellow);
        Debug.DrawLine(new Vector3(steeringAxleBackRight.position.x, 0f, steeringAxleBackRight.position.z) , centerOfTurningCircle, Color.darkOrange);
        Debug.DrawLine(new Vector3(steeringAxleBackLeft.position.x, 0f, steeringAxleBackLeft.position.z) , centerOfTurningCircle, Color.yellow);
        
        
        // Remove these placeholder lines
        Debug.DrawLine(transform.position, transform.position + transform.forward * 10f, Color.red);
        Debug.DrawLine(transform.position, targetPosition, Color.darkGreen);
        DebugDrawCircle(centerOfTurningCircle, 12f, 100, Color.yellow);
        DebugDrawCircle(centerOfTurningCircle - Vector3.right , 14f, 100, Color.yellow);
    }
    
    public static void DebugDrawCircle(Vector3 position, float radius, int segments, Color color)
    {
        if (radius <= 0.0f || segments <= 0)
            return;
        
        var angleStep = (360.0f / segments);
        
        angleStep *= Mathf.Deg2Rad;
 
       
        var lineStart = Vector3.zero;
        var lineEnd = Vector3.zero;
 
        for (int i = 0; i < segments; i++)
        {
            lineStart.x = Mathf.Cos(angleStep * i) ;
            lineStart.z = Mathf.Sin(angleStep * i);
            
            lineEnd.x = Mathf.Cos(angleStep * (i + 1));
            lineEnd.z = Mathf.Sin(angleStep * (i + 1));
            
            lineStart *= radius;
            lineEnd *= radius;
            
            lineStart += position;
            lineEnd += position;
            
            Debug.DrawLine(lineStart, lineEnd, color);
        }
    }
    
    public static Tuple<Vector2, float> circleFromThreePoints(Vector2 point1, Vector2 point2, Vector2 point3) {
        return circleFromThreePoints(point1.x, point1.y, point2.x, point2.y,  point3.x, point3.y);
    }

    public static Tuple<Vector2, float> circleFromThreePoints(float x1, float y1, float x2, float y2, float x3, float y3) {
        var x12 = x1 - x2;
        var x13 = x1 - x3;
        var y12 = y1 - y2;
        var y13 = y1 - y3;
        var y31 = y3 - y1;
        var y21 = y2 - y1;
        var x31 = x3 - x1;
        var x21 = x2 - x1;
        var sx13 = (float)(Math.Pow(x1, 2) - Math.Pow(x3, 2));
        var sy13 = (float)(Math.Pow(y1, 2) - Math.Pow(y3, 2));
        var sx21 = (float)(Math.Pow(x2, 2) - Math.Pow(x1, 2));
        var sy21 = (float)(Math.Pow(y2, 2) - Math.Pow(y1, 2));
        var f = (sx13 * x12 + sy13 * x12 + sx21 * x13 + sy21 * x13) / (2 * (y31 * x12 - y21 * x13));
        var g = (sx13 * y12 + sy13 * y12 + sx21 * y13 + sy21 * y13) / (2 * (x31 * y12 - x21 * y13));
        var c = -(float)Math.Pow(x1, 2) - (float)Math.Pow(y1, 2) - 2 * g * x1 - 2 * f * y1;
        var resultX = -g;
        var resultY = -f;
        var radius = (float)Math.Sqrt( resultX * resultX + resultY * resultY - c);
        return new Tuple<Vector2, float>(new Vector2(resultX, resultY), radius);
    }
}
