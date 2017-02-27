USE [admin]
GO

/****** Object:  Table [dbo].[admins]    Script Date: 2/27/2017 3:25:09 PM ******/
DROP TABLE [dbo].[admins]
GO

/****** Object:  Table [dbo].[admins]    Script Date: 2/27/2017 3:25:09 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[admins](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](60) NOT NULL,
	[email] [varchar](60) NOT NULL,
	[passwords] [varchar](20) NOT NULL,
	[status] [int] NOT NULL,
	[creat_at] [datetime] NOT NULL CONSTRAINT [DF_admins_creat_at]  DEFAULT (getdate()),
	[update_at] [datetime] NULL CONSTRAINT [DF__admins__update_a__108B795B]  DEFAULT (getdate()),
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO


