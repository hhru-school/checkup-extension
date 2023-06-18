import { Link } from "react-router-dom";
import logo from "./logo.svg";

export const CheckupLogo = () => {
  return (
    <Link to={"/"}>
      <img src={logo} alt="CheckUp Extension" />
    </Link>
  );
};
