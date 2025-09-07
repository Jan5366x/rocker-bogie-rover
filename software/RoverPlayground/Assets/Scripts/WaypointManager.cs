using UnityEngine;
using UnityEngine.Splines;

[ExecuteInEditMode]
public class WaypointManager : MonoBehaviour
{
    private RoverWaypoint[] _waypoints;
    private int _childCount;
    private SplineContainer _splineContainer;
    
    void Awake()
    {
        ReloadWaypoints();
    }

    void Update()
    {
        _splineContainer = transform.GetComponentInChildren<SplineContainer>();
        // Reload waypoints if the number of children has changed
        if (_childCount != transform.childCount)
        {
            ReloadWaypoints();
            _childCount = transform.childCount;
        }
        
        // Draw debug lines between waypoints
        RoverWaypoint lastWaypoint = null;
        _splineContainer.Splines[0].Clear();
        foreach (var roverWaypoint in _waypoints)
        {
            var bezierKnot = new BezierKnot(roverWaypoint.transform.position);
            _splineContainer.Splines[0].Add(bezierKnot);
            if (lastWaypoint)
            {
                Debug.DrawLine(lastWaypoint.transform.position, roverWaypoint.transform.position, Color.aquamarine);
            }
            lastWaypoint = roverWaypoint;
        }
        _splineContainer.Splines[0].SetTangentMode(TangentMode.AutoSmooth);
    }

    private void ReloadWaypoints()
    { 
       
        _waypoints = transform.GetComponentsInChildren<RoverWaypoint>();
    }
}
