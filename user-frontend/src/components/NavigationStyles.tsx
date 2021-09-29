import styled from "styled-components";
import {FaBars} from "react-icons/fa"
import {NavLink as Link} from 'react-router-dom'

export const Nav= styled.nav`
  background: #76CC4D;
  height: 150px;
  display: flex;
  justify-content: space-between;
  padding-right: 60px;
  z-index: 10;
`
export const Bars = styled(FaBars) `
  display: none;
  color: #fff;

  @media screen and (max-width: 800px) {
    display: block;
    position: absolute;
    top: 0;
    right:0;
    transform: translate(-100%,75%);
    font-size: 1.8rem;
    cursor: pointer;
  }
`
export const NavLink = styled(Link)`
  color: #fff;
  display: flex;
  align-items: center;
  text-decoration: none;
  padding: 0 1rem;
  height: 100%;
  text-transform: uppercase;
  cursor: pointer;
  opacity: 0.8;

  &.active {
    opacity: 1;
    text-decoration: underline;
  }
`
export const NavMenu = styled.div`
  display: flex;
  align-items: center;
  margin-right: -24px;
  
  @media screen and (max-width: 800px){
    display: none;
  }
`
