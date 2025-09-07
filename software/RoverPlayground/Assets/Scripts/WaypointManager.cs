using UnityEngine;

[ExecuteInEditMode]
public class WaypointManager : MonoBehaviour
{
    private RoverWaypoint[] waypoints;
    private int childCount;
    
    void Awake()
    {
        ReloadWaypoints();
    }

    void Update()
    {
        // Reload waypoints if the number of children has changed
        if (childCount != transform.childCount)
        {
            ReloadWaypoints();
            childCount = transform.childCount;
        }
        
        // Draw debug lines between waypoints
        RoverWaypoint lastWaypoint = null;
        foreach (var roverWaypoint in waypoints)
        {
            if (lastWaypoint)
            {
                Debug.DrawLine(lastWaypoint.transform.position, roverWaypoint.transform.position, Color.aquamarine);
            }
            lastWaypoint = roverWaypoint;
        }
    }

    private void ReloadWaypoints()
    { 
        waypoints = transform.GetComponentsInChildren<RoverWaypoint>();
    }
}
