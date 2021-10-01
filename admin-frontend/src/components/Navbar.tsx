import React, {useState} from 'react';
import * as AiIcons from 'react-icons/ai';
import {Link} from 'react-router-dom';
import './Navbar.css'
import { SideNavbar } from './SideNavbarData';
import baytreeLogo from '../assets/baytree-logo.svg'

function Navbar() {
  const [sidebar, setSidebar] = useState(false);

  const showSidebar = () => setSidebar(!sidebar);

  return (
    <>
        <div className='navbar'>
            <img className = 'baytreeLogo'
                src = {baytreeLogo}
                alt = {'Baytree-logo'} 
                width = '60px'/>
            <Link to='#' className='menu-bars'>
                <AiIcons.AiOutlineBars onClick={showSidebar} />
            </Link>
        </div>
        <nav className={sidebar ? 'nav-menu active' : 'nav-menu'}>
            <ul className='nav-menu-items' onClick={showSidebar}>
                <li className='navbar-toggle'>
                    <Link to='#' className='menu-bars'>
                        <AiIcons.AiOutlineClose />
                    </Link>
                </li>
            {SideNavbar.map((item, index) => {
                return (
                    <li key={index} className={item.cName}>
                    <Link to={item.path}>
                        {item.icon}
                        <span>{item.title}</span>
                    </Link>
                    </li>
                );
            })}
            </ul>
        </nav>
    </>
  );
}

export default Navbar;