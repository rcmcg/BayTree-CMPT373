import React from 'react'

type Props = {
  title: string
}

const Tab: React.FunctionComponent<Props> = ({ children }) => {
  return <div>{children}</div>
}

export default Tab