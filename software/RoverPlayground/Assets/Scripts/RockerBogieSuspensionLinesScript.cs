using System;
using UnityEngine;

[ExecuteInEditMode]
public class RockerBogieSuspensionLinesScript : MonoBehaviour
{
    private Transform _steeringAxleFront;
    private Transform _steeringAxleMiddle;
    private Transform _steeringAxleBack;
    private Transform _bogie;
    
    // Start is called once before the first execution of Update after the MonoBehaviour is created
    void Start()
    {

        _steeringAxleBack = transform.Find("steering axle back");
        _bogie = transform.Find("Bogie");
        _steeringAxleFront = _bogie.Find("steering axle front");
        _steeringAxleMiddle = _bogie.Find("steering axle middle");
    }

    private void Awake()
    {
        _steeringAxleBack = transform.Find("steering axle back");
        _bogie = transform.Find("Bogie");
        _steeringAxleFront = _bogie.Find("steering axle front");
        _steeringAxleMiddle = _bogie.Find("steering axle middle");
    }

    // Update is called once per frame
    void Update()
    {
        Debug.DrawLine(_steeringAxleFront.transform.position, _bogie.position, Color.red);
        Debug.DrawLine(_steeringAxleMiddle.transform.position, _bogie.position, Color.red);
        Debug.DrawLine(_steeringAxleBack.transform.position, transform.position, Color.blue);
        Debug.DrawLine(_bogie.transform.position, transform.position, Color.blue);
    }
}
