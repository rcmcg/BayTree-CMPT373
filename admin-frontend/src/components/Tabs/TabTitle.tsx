import React, { useCallback } from "react"
import './styles/Tabs.css'

type Props = {
  title: string
  index: number
  setSelectedTab: (index: number) => void
}

const TabTitle: React.FunctionComponent<Props> = ({ title, setSelectedTab, index }) => {

  const onClick = useCallback(() => {
    setSelectedTab(index)
  }, [setSelectedTab, index])

  return (
    <li>
      <button onClick={onClick} className = 'button'>{title}</button>
    </li>
  )
}

export default TabTitle