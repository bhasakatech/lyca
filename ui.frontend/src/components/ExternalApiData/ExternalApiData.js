import React, { useEffect, useState } from "react";
import "./ExternalApiData.css";

const ExternalApiData = () => {
  const [products, setProducts] = useState([]);
  const [status, setStatus] = useState("loading");

  useEffect(() => {
    fetch("/bin/external/api/data")
      .then((res) => res.json())
      .then((res) => {
        setStatus(res.status);

        if (res.status === "success") {
          setProducts(res.data);
        }
      })
      .catch(() => setStatus("error"));
  }, []);

  if (status === "disabled") {
    return (
      <div className="api-data-message">
        API Disabled via OSGi Config
      </div>
    );
  }

  if (status === "error") {
    return <div className="api-data-message">Error fetching data</div>;
  }

  return (
    <div className="api-data-container">
      <h2 className="title">API Data List</h2>

      <table className="api-data-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>TITLE</th>
            <th>COMPLETED</th>
          </tr>
        </thead>

        <tbody>
          {products.map((item) => (
            <tr key={item.id}>
              <td>{item.id}</td>
              <td>{item.title}</td>
              <td>{item.completed ? "Yes" : "No"}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ExternalApiData;