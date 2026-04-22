import React, { useEffect, useState } from "react";
import "./DataIntegrationTable.css";
export const DataIntegrationTable = () => {

  const [data, setData] = useState([]);
  const [status, setStatus] = useState("");

  useEffect(() => {
    fetch("/bin/api-table-data")
      .then(res => res.json())
      .then(res => {
        console.log("API DATA:", res);

        //Handle both formats
        if (res.status === "disabled") {
          setStatus("disabled");
        } else if (res.status === "success") {
          setData(res.data);
        } else {
          //  If raw array comes
          setData(res);
        }
      })
      .catch(err => console.error("Error:", err));
  }, []);

  //  If disabled via OSGi config
  if (status === "disabled") {
    return <h3>Data is disabled in this environment</h3>;
  }

  return (
    <div className="data-integration-table">
      <h2>Data Integration Table</h2>

      <table border="1">
        <thead>
          <tr>
            {data.length > 0 &&
              Object.keys(data[0]).map((key) => (
                <th key={key}>{key}</th>
              ))}
          </tr>
        </thead>

        <tbody>
          {data.map((item, index) => (
            <tr key={index}>
              {Object.values(item).map((val, i) => (
                <td key={i}>
                  {typeof val === "object"
                    ? JSON.stringify(val) 
                    : val}
                </td>
              ))}
            </tr>
          ))}
        </tbody>
      </table>

      {data.length === 0 && <p>No data available</p>}
    </div>
  );
};