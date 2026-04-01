import React from "react";
import { MapTo } from "@adobe/aem-react-editable-components";

//  Edit Config (important for AEM Authoring)
const AuthorEditConfig = {
  emptyLabel: "Author Component",

  isEmpty: function (props) {
    return !props || (!props.name && !props.desc);
  }
};

const Author = (props) => {

  console.log("AUTHOR PROPS:", props); // Debug

  //  Author mode me empty state show hoga
  if (!props || (!props.name && !props.desc)) {
    return (
      <div style={{
        border: "1px dashed red",
        padding: "10px",
        background: "#fff3f3"
      }}>
        <h3>Author Component (Empty)</h3>
        <p>Please configure this component</p>
      </div>
    );
  }

  return (
    <div style={{
      border: "1px solid #ccc",
      padding: "15px",
      borderRadius: "8px",
      background: "#f9f9f9"
    }}>
      <h2 style={{ marginBottom: "10px" }}>AUTHOR COMPONENT</h2>

      <h3>
        <strong>Name:</strong> {props.name}
      </h3>

      <p>
        <strong>Description:</strong> {props.desc}
      </p>
    </div>
  );
};

// Mapping (must match Sling Model resourceType)


export default Author;
