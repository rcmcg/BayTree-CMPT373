import React, {useState} from 'react'
import { IconContext } from 'react-icons';
import {FaBars, FaTimes} from "react-icons/fa";
import {NavLink as Link} from "react-router-dom";
import "../css/Navbar.css"
import baytreeLogo from "../resources/baytree-logo.svg"

const NavigationBar = () => {

    const [click, setClick] = useState(false)

    const handleClick = () => setClick(!click)
    const closeMobileMenu = () => setClick(false)

    return (

        <IconContext.Provider value={{ color: '#ffffff' }}>
            <nav className={"navbar"}>

                <div className={"navbar-container container"}>
                    <Link to={"/"}>
                        <img className={"brand-logo"}
                             src={baytreeLogo}
                             alt={"Baytree-logo"}/>
                    </Link>
                </div>

                <div className={"menu-icon"} onClick={handleClick}>
                    {click ? <FaTimes/> : <FaBars/>}
                </div>

                <ul className={click ? "nav-menu-active" : "nav-menu"}>

                    <li className={"nav-item"}>
                        <Link activeClassName={"active"} exact to={'/'} className="nav-links" onClick={closeMobileMenu}>
                            <a>Dashboard</a>
                        </Link>
                    </li>
                    <li className={"nav-item"}>
                        <Link to={'/resources'} className="nav-links" onClick={closeMobileMenu}>
                            <a>Resources</a>
                        </Link>
                    </li>
                    <li className={"nav-item"}>
                        <Link to={'/profile'} className="nav-links" onClick={closeMobileMenu}>
                            <a>Profile</a>
                        </Link>
                    </li>

                </ul>

            </nav>

            <section className="header-background"/>

        </IconContext.Provider>
    );
};

export {
    NavigationBar
}
